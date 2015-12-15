(ns aoc.day14-test
  (:require [clojure.test :refer :all]
            [aoc.day14 :refer :all]))
            
(deftest testing-distance-traveled
  (testing "Testing Comet, who can 14 km/s for 10 seconds, 
            but then must rest for 127 seconds."
    (let [comet {:speed 14 :max-flying-time 10 :min-rest-time 127}]
      (testing "After one second"
        (is (= 14 (distance-traveled 1 comet))))
      (testing "After ten seconds"
        (is (= 140 (distance-traveled 10 comet))))
      (testing "After 11 seconds"
        (is (= 140 (distance-traveled 11 comet))))
      (testing "After 12 seconds"
        (is (= 140 (distance-traveled 12 comet))))
      (testing "After 148 seconds"
        (is (= 280 (distance-traveled 148 comet))))
      (testing "After 185 seconds"
        (is (= 280 (distance-traveled 185 comet))))
      (testing "After 1000 seconds"
        (is (= 1120 (distance-traveled 1000 comet))))))
  (testing "Testing Dancer, who can 16 km/s for 11 seconds, 
            but then must rest for 162 seconds."
    (let [comet {:speed 16 :max-flying-time 11 :min-rest-time 162}]
      (testing "After one second"
        (is (= 16 (distance-traveled 1 comet))))
      (testing "After 10 seconds"
        (is (= 160 (distance-traveled 10 comet))))
      (testing "After 11 seconds"
        (is (= 176 (distance-traveled 11 comet))))
      (testing "After 12 seconds"
        (is (= 176 (distance-traveled 12 comet))))
      (testing "After 148 seconds"
        (is (= 176 (distance-traveled 148 comet))))
      (testing "After 174 second"
        (is (= 192 (distance-traveled 174 comet))))
      (testing "After 185 seconds"
        (is (= 352 (distance-traveled 185 comet))))
      (testing "After 1000 seconds"
        (is (= 1056 (distance-traveled 1000 comet))))
        )))
