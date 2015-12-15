; (ns aoc.day11-test
;   (:require [clojure.test :refer :all]
;             [aoc.day11 :refer :all]))
; 
; (deftest testing-rule2
;   (testing "Passwords that fail rule2"
;     (is (false? (passes-rule2? "foo")))
;     (is (false? (passes-rule2? "fizz")))
;     (is (false? (passes-rule2? "fall")))
;     (is (false? (passes-rule2? "foil"))))
;   (testing "Passwords that pass rule2"
;     (is (passes-rule2? "bar"))
;     (is (passes-rule2? "baz"))
;     (is (passes-rule2? "quuz"))
;     (is (passes-rule2? "xyzzy"))))
; 
; (deftest testing-rule3
;   (testing "Passwords that fail rule3"
;     (is (false? (passes-rule3? "foo")))))
