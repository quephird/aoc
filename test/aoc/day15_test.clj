(ns aoc.day15-test
  (:require [clojure.test :refer :all]
            [aoc.day15 :refer :all]))
            
(deftest testing-get-ingedient-data
  (testing "Loading and parsing of data"
    (is (= [["Butterscotch" {:capacity -1 :durability -2 :flavor 6 :texture 3 :calories 8}]
            ["Cinnamon" {:capacity 2 :durability 3 :flavor -2 :texture -1 :calories 3}]]
           (get-ingredient-data "test/aoc/day15_test.txt")))))

(deftest testing-score-cookie
  (testing "Testing scoring of cookie with specific proportions"
    (let [ingredient-data (get-ingredient-data "test/aoc/day15_test.txt")]
      (= 62842880 (score-cookie ingredient-data [44 56])))))
