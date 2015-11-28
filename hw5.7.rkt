#lang racket

;;;Shal Xu HW5.7

;;;find the smallest element in a list
(define (minimum list)
  (cond ((equal? (length list) 1) (first list))
        ((< (first list) (minimum (rest list))) (first list))
        (else (minimum (rest list)))))
         
;;Create a sublist of lista, starting at start index and ending after size. Index starts at 0
(define (sublist start size lista)
 (cond((> (+ start size) (length lista)) (write ("sublist error")))
       ((and (equal? start 0) (equal? size 1))  (list (first lista)))
       ((> start 0) (sublist (- start 1) size (rest lista)))
       (else (cons (first lista) (sublist start (- size 1) (rest lista))))))

;;;Calculate the distance between two lists (same length)         
(define (distance lista listb)
  (cond ((and (empty? lista) (empty? listb)) 0)
        ((equal? (length lista) (length listb)) (+ (abs (- (first lista) (first listb))) (distance (rest lista) (rest listb))))
        (else (write "Distance error"))))

;;;Calculate the distance between a list and all possible sublists of the other, and combine all the distances into a list to return
(define (distanceList lista listb)
  (cond ((> (length lista) (length listb)) write "Distance List error")
        ((= (length lista) (length listb)) (list (distance lista listb)))
        (else (cons (distance lista (sublist 0 (length lista) listb)) (distanceList lista (rest listb)))))) 

;;;Returns the index of an element in a list
(define (search list number count)
  (cond ((equal? (first list) number) count)
        (else (search (rest list) number (+ count 1))))) 

;;;Report result
(define (shortest lista listb)
  (write "S:")
  (write lista)
  (write-char #\newline)
  (write "T:")
  (write listb)
  (write-char #\newline)
  (write "The sublist of T has the shortest distance of ")
  (write (minimum (distanceList lista listb)))
  (write-char #\newline)
  (write "The sublist of T which has the shortest distance to S starts at index ")
  (write(search (distanceList lista listb) (minimum (distanceList lista listb)) 0))
  (write-char #\newline)
  (write-char #\newline)
  )

(shortest '(1 2 3) '(0 2 4))
(shortest '(0 2 3) '(1 0 2 4))
 