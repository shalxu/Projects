#lang racket

;;;Shal Xu Hw5.8
;;;syntax: (process StringToBeProcessed)

;;;parser and lexer for processing strings of operations of integer sets.
;;;e.g.C {1,2,3,4,7,8} would evaluate to {0,5,6,9}
;;;U {1,2,3g f7,8,9} would evaluate to {1,2,3,7,8,9}
;;;I U {1,2,3g f7,8,9} C {1,2,3,4,7,8}would evaluate to {9}

(require parser-tools/lex
         parser-tools/yacc
         data/integer-set)
;;;define tokens

(define-tokens v (VAR))
(define-empty-tokens e (COMPLEMENT INTERSECTION UNION LEFTPAREN RIGHTPAREN EOF COMMA))

;;;Lexer
(define setLexer
  (lexer
   [#\{ (token-LEFTPAREN)]
   [#\} (token-RIGHTPAREN)]
   [#\, (token-COMMA)]
   [#\C (token-COMPLEMENT)]
   [#\U (token-UNION)]
   [#\I (token-INTERSECTION)]
   [(repetition 1 +inf.0 (char-range #\0 #\9))
        (token-VAR lexeme)]
   [whitespace (setLexer input-port)]
   [(eof) (token-EOF)]))

;;;struct for parser.

(struct Set (set) #:transparent) ;;; a set of numbers stored in set
(struct Complement (set) #:transparent) ;;; Operations, variables stores operands to be processed
(struct Union (set1 set2) #:transparent) 
(struct Intersection (set1 set2) #:transparent) 

;;;Parser

(define setParser
  (parser
   (start expr)
   (end EOF)
   (tokens v e)
   (error void)
   (grammar
    (expr
          ((COMPLEMENT expr) (Complement $2))
          ((INTERSECTION expr expr) (Intersection $2 $3))
          ((UNION expr expr) (Union $2 $3))
          ((VAR COMMA expr) (cons (string->number $1) $3))
          ((LEFTPAREN expr RIGHTPAREN) (Set $2))
          ((VAR) (list (string->number $1)))
          ))
   ))

(define (lex-this lexer input)
  (lambda () (lexer input)))

(define (evalHelper expr)
  (cond
    ((Set? expr) (Set-set expr))
    ((Union? expr) (UnionOp (evalHelper(Union-set1 expr)) (evalHelper(Union-set2 expr))))
    ((Complement? expr) (ComplementOp (evalHelper(Complement-set expr))))
    ((Intersection? expr) (IntersectionOp (evalHelper(Intersection-set1 expr)) (evalHelper(Intersection-set2 expr))))
    (else (write "evalHelper error"))))

;;; Process the set (can be list) to do complement operation, compared to integers 0-9

(define (ComplementOp set)
  (cond ((set? set) (list->set (ComplementHelper set (list->set '(0 1 2 3 4 5 6 7 8 9)))))
        (else (list->set (ComplementHelper (list->set set) (list->set '(0 1 2 3 4 5 6 7 8 9)))))))

;;; Complement the given set compared to control set

(define (ComplementHelper set control)
  (cond ((set-empty? control) '())
        ((set-member? set (set-first control)) (ComplementHelper set (set-rest control)))
        (else (cons (set-first control) (ComplementHelper set (set-rest control))))))

;;;Process the sets (can be lists) to do Intersection operation

(define (IntersectionOp set1 set2)
  (cond ((and (set? set1) (set? set2)) (set-intersect set1 set2))
        ((and (set? set1) (not (set? set2))) (set-intersect set1 (list->set set2)))
        ((and (not (set? set1)) (set? set2)) (set-intersect (list->set set1) set2))
        (else (set-union (list->set set1) (set-intersect set2)))))

;;;Process the sets (can be lists) to do Union operation

(define (UnionOp set1 set2)
   (cond ((and (set? set1) (set? set2)) (set-union set1 set2))
        ((and (set? set1) (not (set? set2))) (set-union set1 (list->set set2)))
        ((and (not (set? set1)) (set? set2)) (set-union (list->set set1) set2))
        (else (set-union (list->set set1) (list->set set2)))))

;;;Integeration

(define (process in)
  (evalHelper (setParser (lex-this setLexer (open-input-string in)))))

;;;test inputs

(define input1 "U {1,2,3} {3,6}")
(define input2 "C {1,2,3,4,7,8}")
(define input3 "U {1,2,3} {7,8,9}")
(define input4 "I U {1,2,3} {7,8,9} C {1,2,3,4,7,8}")

;;;test result

(process input4)

           