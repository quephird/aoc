; (ns aoc.day07-test
;   (:require [clojure.test :refer :all]
;             [aoc.day07 :refer :all]))
; 
; (deftest testing-simple-circuit
;   (testing "Testing simple circuit"
;     (let [input ["123 -> x"
;                  "456 -> y"
;                  "x AND y -> d"
;                  "x OR y -> e"
;                  "x LSHIFT 2 -> f"
;                  "y RSHIFT 2 -> g"
;                  "NOT x -> h"
;                  "NOT y -> i"]] 
;       (is (= {:d 72
;               :e 507
;               :f 492
;               :g 114
;               :h 65412
;               :i 65079
;               :x 123
;               :y 456}
;              (run-circuit input))))))
