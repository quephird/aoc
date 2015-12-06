(ns aoc.day03-test
  (:require [clojure.test :refer :all]
            [aoc.day03 :refer :all]))

(deftest testing-move
  (testing "Moving right"
    (is (= [1 0] (move [0 0] \>))))
  (testing "Moving left"
    (is (= [-1 0] (move [0 0] \<))))
  (testing "Moving up"
    (is (= [0 1] (move [0 0] \^))))
  (testing "Moving down"
    (is (= [0 -1] (move [0 0] \v))))
  (testing "Not moving because of meaningless direction"
    (is (= [0 0] (move [0 0] \h)))))

(deftest testing-deliver-gift
  (testing "Go north from start, and stop"
    (is (= [[0 1] {[0 0] 1 [0 1] 1}]
           (deliver-gift [[0 0] {[0 0] 1}] \^))))
  (testing "Go south from start, and stop"
    (is (= [[0 -1] {[0 0] 1 [0 -1] 1}]
           (deliver-gift [[0 0] {[0 0] 1}] \v))))
  (testing "Go east from start, and stop"
    (is (= [[-1 0] {[0 0] 1 [-1 0] 1}]
           (deliver-gift [[0 0] {[0 0] 1}] \<))))
  (testing "Go west from start, and stop"
    (is (= [[1 0] {[0 0] 1 [1 0] 1}]
           (deliver-gift [[0 0] {[0 0] 1}] \>))))
  (testing "Go back south, and stop"
    (is (= [[0 0] {[0 0] 2 [0 1] 1}]
           (deliver-gift [[0 1] {[0 0] 1 [0 1] 1}] \v)))))

(deftest testing-total-houses-delivered
  (testing "Santa delivers to two houses"
    (is (= 2 (total-houses-delivered ">"))))
  (testing "Santa delivers to two houses"
    (is (= 3 (total-houses-delivered ">>"))))
  (testing "Santa delivers to four houses"
    (is (= 4 (total-houses-delivered "^>v<"))))
  (testing "Santa delivers to two very lucky houses"
    (is (= 2 (total-houses-delivered "^v^v^v^v^v")))))

(deftest testing-deliver-gift'
  (let [init-state [[[0 0] {[0 0] 1}]
                    [[0 0] {[0 0] 1}]]] 
    (testing "Santa goes up, robo goes down"
      (is (= [[[0 1] {[0 0] 1 [0 1] 1}]
              [[0 -1] {[0 0] 1 [0 -1] 1}]]
             (deliver-gift' init-state [\^ \v]))))
    (testing "Santa goes up, robo goes right"
      (is (= [[[0 1] {[0 0] 1 [0 1] 1}]
              [[1 0] {[0 0] 1 [1 0] 1}]]
             (deliver-gift' init-state [\^ \>]))))))

(deftest testing-total-houses-delivered
  (testing "Santa goes up, robo goes down"
    (is (= 3 (total-houses-delivered' "^v"))))
  (testing "Santa goes up, robo goes down, both go back home."
    (is (= 3 (total-houses-delivered' "^>v<"))))
  (testing "Santa keeps going up, robo keeps going down."
    (is (= 11 (total-houses-delivered' "^v^v^v^v^v")))))

