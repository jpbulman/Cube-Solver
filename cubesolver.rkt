;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-reader.ss" "lang")((modname cubesolver) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define-struct cube (e1 e2 e3 e4 e5 e6 e7 e8 e9 e10 e11 e12 c1 c2 c3 c4 c5 c6 c7 c8 solution))
(define-struct edge (sticker1 sticker2))
(define-struct corner (sticker1 sticker2 sticker3))

;Assuming a blue front yellow top for the cube

(define solvedcube (make-cube (make-edge 2 4) (make-edge 2 6) (make-edge 2 3) (make-edge 2 5) (make-edge 4 6) (make-edge 3 6) (make-edge 3 5) (make-edge 4 5) (make-edge 1 3)
                              (make-edge 1 6) (make-edge 1 4) (make-edge 1 5) (make-corner 2 4 6) (make-corner 2 5 4) (make-corner 2 3 5) (make-corner 2 6 3)
                              (make-corner 1 3 6) (make-corner 1 5 3) (make-corner 1 4 5) (make-corner 1 6 4) ""))


;Color code
; 1-White
; 2-Yellow
; 3-Blue
; 4-Green
; 5-Red
; 6-Orange

;Corners are (starting at the top left and going left, then down left once you hit the end) 1 2 3 4, then the bottom 5 6 7 8
                                                                               
;Edges 1-12 start at the top back, go ccw around the layer, move down to equtor, start at back left and go ccw, then go to the bottom layer and start at the DF edge and
;go ccw
                                         
; URF Corners on the top layer have sticker1 starting on the top, then going ccw, sticker2 front, and sticker3
; DLF Corners on the bottom layer have sticker1 on the bottom, going ccw, sticker2 front, and then sticker3 left

;Top layer edges have sticker1 on top and sticker2 on front
;Front equator egdes have sticker1 front and sticker2 left | right
;Back equator edges have sticker1 on the back and sticker2 left | right
;Bottom edges have sticker1 on the bottom and sticker2 front

;Piece = Corner | Edge



;Edge=?
;Edge Edge -> Boolean
;Determines if the edges are equal
;Tests:
(check-expect (edge=? (make-edge 1 3) (make-edge 1 3)) true)
(check-expect (edge=? (make-edge 1 4) (make-edge 1 3)) false)

(define (edge=? e1 e2)
  (if (and (= (edge-sticker1 e1) (edge-sticker1 e2)) (= (edge-sticker2 e1) (edge-sticker2 e2)))
      true
      false))

;Oriented?
;Signature: Piece -> Boolean
;Checks if the piece is oriented properly
;Tests:
(check-expect (oriented? (make-corner 6 2 4)) false)
(check-expect (oriented? (make-corner 2 5 6)) true)
(check-expect (oriented? (make-edge 2 4)) true)
(check-expect (oriented? (make-edge 5 3)) false)
 
(define (oriented? p1)
  (if (edge? p1)
      (if (< (edge-sticker1 p1) 5)
      true
      false)
      (if (< (corner-sticker1 p1) 3)
      true
      false)))

;Solution?
;String Cube -> Boolean
;Checks to see if the string is a solution to the cubestate
;(define (solution? string cube) true)

;;Solved?
;;Cube -> Boolean
;;To see if a given cube is solved
;;!!!
;(define (solved? cube)
;  (if (and (= (edge-sticker1 (cube-e1 cube) (cube-e2 cube)))

;Permuted?
;Cube Int -> Boolean
;Checks to see if the piece in the nth edge spot is correct
;!!!
;(define (permuted? p1 spot-num)
;  (if (edge? p1)
;      (if (= (sticker-1 p1) (sticker-1 (edge1 solvedcube))))))
  

;Solves a piece
;(define (solvepiece p1)
 ; (if (oriented? p1)
      ;(permute p1)
      ;(permute (orient p1))))





