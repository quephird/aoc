(ns aoc.day06-test
  (:require [clojure.test :refer :all]
            [aoc.day06 :refer :all]))

(deftest testing-turn-on
  (testing "Turning on every light"
    (let [lights {[0 0] false [0 1] true  [0 2] false
                  [1 0] true  [1 1] false [1 2] true
                  [2 0] false [2 1] true  [2 2] false}] 
      (is (= {[0 0] true [0 1] true [0 2] true
              [1 0] true [1 1] true [1 2] true
              [2 0] true [2 1] true [2 2] true}
            (turn-on [0 0] [2 2] lights))))))

(deftest testing-turn-off
  (testing "Turning off every light"
    (let [lights {[0 0] false [0 1] true  [0 2] false
                  [1 0] true  [1 1] false [1 2] true
                  [2 0] false [2 1] true  [2 2] false}] 
      (is (= {[0 0] false [0 1] false [0 2] false
              [1 0] false [1 1] false [1 2] false
              [2 0] false [2 1] false [2 2] false}
            (turn-off [0 0] [2 2] lights))))))

(deftest testing-toggle
  (testing "Toggling every light"
    (let [lights {[0 0] false [0 1] true  [0 2] false
                  [1 0] true  [1 1] false [1 2] true
                  [2 0] false [2 1] true  [2 2] false}] 
      (is (= {[0 0] true  [0 1] false [0 2] true 
              [1 0] false [1 1] true  [1 2] false
              [2 0] true  [2 1] false [2 2] true}
            (toggle [0 0] [2 2] lights))))))
