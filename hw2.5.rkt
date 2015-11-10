#lang racket
(require parser-tools/lex)
(require parser-tools/lex-sre)

;;; defines non-empty tokens in the ID category
(define-tokens t (ID))

;;;reads an input string and return a token with a catogory of ID and a value which is the identifier name
(define idLexer
  (lexer
   [(concatenation (char-range #\a #\z) (repetition 0 +inf.0 (union (char-range #\a #\z)(char-range #\0 #\9)(char-range #\A #\Z))))
   (token-ID lexeme)]
   [whitespace (idLexer input-port)]
   [(eof) '()]))


(define input (open-input-string "aEXAMPLE a b"))

