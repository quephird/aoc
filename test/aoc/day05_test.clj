(ns aoc.day05-test
  (:require [clojure.test :refer :all]
            [aoc.day05 :refer :all]))

(deftest testing-nice
  (testing "Nice string"
    (is (nice? "ugknbfddgicrmopn")))
  (testing "Another nice string"
    (is (nice? "aaa")))
  (testing "Naughty string with no double letter"
    (is (not (nice? "jchzalrnumimnmhp"))))
  (testing "Naughty string with xy"
    (is (not (nice? "haegwjzuvuyypxyu"))))
  (testing "Naughty string with only onw vowel"
    (is (not (nice? "dvszwmarrgswjxmb")))))

(deftest testing-nice'
  (testing "Nice' string"
    (is (nice?' "qjhvhtzxzqqjkmpb")))
  (testing "Another nice' string"
    (is (nice?' "xxyxx")))
  (testing "Naughty string"
    (is (not (nice? "uurcxstgmygtbstg"))))
  (testing "Another naughty string"
    (is (not (nice? "ieodomkazucvgmuy")))))
    
