(ns aoc.day02-test
  (:require [clojure.test :refer :all]
            [aoc.day02 :refer :all]))

(deftest testing-total-wrapping-paper
  (testing "2x3x4"
    (is (= 58 (process "2x3x4" wrapping-paper-fn))))
  (testing "1x1x10"
    (is (= 43 (process "1x1x10" wrapping-paper-fn)))))

(deftest testing-total-ribbon
  (testing "2x3x4"
    (is (= 34 (process "2x3x4" ribbon-fn))))
  (testing "1x1x10"
    (is (= 14 (process "1x1x10" ribbon-fn)))))
