(ns aoc.day18-test
  (:require [clojure.test :refer :all]
            [aoc.day18 :refer :all]))

(deftest testing-next-grid
  (let [grid [".#.#.#"
              "...##."
              "#....#"
              "..#..."
              "#.#..#"
              "####.."]]
    (testing "Step 1"
      (is (= ["..##.."
              "..##.#"
              "...##."
              "......"
              "#....."
              "#.##.."]
            (next-grid grid))))
    (testing "After four steps"
      (is (= ["......"
              "......"
              "..##.."
              "..##.."
              "......"
              "......"]
            (nth (iterate next-grid grid) 4))))))
