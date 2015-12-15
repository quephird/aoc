(ns aoc.day12-test
  (:require [clojure.test :refer :all]
            [aoc.day12 :refer :all]))

(deftest testing-extract-numbers
  (testing "Vectors"
    (testing "Empty vector"
      (is (= nil
             (extract-numbers []))))
    (testing "Vector with only numbers"
      (is (= '(1 2 3)
             (extract-numbers [1 2 3]))))
    (testing "Vector with mixed simple types"
      (is (= '(1)
             (extract-numbers [1 "2" :3]))))
    (testing "Vector with composite types"
      (is (= '((1 2) (3 4))
             (extract-numbers [[1 2] [3 4]]))))
    (testing "Vector with simple and composite types"
      (is (= '((1 2) 3 (4 5) 6)
             (extract-numbers [[1 2] 3 [4 5] 6])))))
  (testing "Strings"
    (testing "Strings should not be included"
      (is (= nil (extract-numbers "Hi there"))))))
