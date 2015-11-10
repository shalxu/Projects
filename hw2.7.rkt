#lang racket
(require parser-tools/lex)
(require parser-tools/lex-sre)

;;;defines tokens for boolean values and operators
(define-tokens v (VALUE LEFTPAREN RIGHTPAREN BINARYOP UNARYOP))
;;;produce a list of tokens for boolean expressions
(define constantBoolLexer
    (lexer
   ["true" (cons (token-VALUE lexeme) (constantBoolLexer input-port))]
   ["false" (cons (token-VALUE lexeme)(constantBoolLexer input-port))]
   [#\( (cons (token-LEFTPAREN lexeme)(constantBoolLexer input-port))]
   [#\) (cons (token-RIGHTPAREN lexeme)(constantBoolLexer input-port))]
   ["and" (cons (token-BINARYOP lexeme)(constantBoolLexer input-port))]
   ["or" (cons (token-BINARYOP lexeme)(constantBoolLexer input-port))]
   ["xor" (cons (token-BINARYOP lexeme)(constantBoolLexer input-port))]
   ["not" (cons (token-UNARYOP lexeme)(constantBoolLexer input-port))]
   [whitespace (constantBoolLexer input-port)]
   [(eof) '()]))
(define test-input (open-input-string "true and false)"))
