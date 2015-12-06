(ns aoc.day04-test
  (:require [clojure.test :refer :all]
            [aoc.day04 :refer :all]))

(deftest testing-solution
  (testing "Known solution for abcdef"
    (is (= 609043 (solution "abcdef" "00000"))))
  (testing "Known solution for pqrstuv"
    (is (= 1048970 (solution "pqrstuv" "00000")))))
