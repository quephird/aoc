(ns aoc.day19-test
  (:require [clojure.test :refer :all]
            [aoc.day19 :refer :all]))

(deftest testing-distinct-replacements
  (testing "Simple molecule"
    (let [molecule     "HOH"
          replacements [["H" "OH"]
                        ["H" "HO"]
                        ["O" "HH"]]]
      (is (= #{"HOOH" "HOHO" "OHOH" "HHHH"}
            (distinct-replacements molecule replacements)))))
  (testing "Santa's favorite molecule"
    (let [molecule     "HOHOHO"
          replacements [["H" "OH"]
                        ["H" "HO"]
                        ["O" "HH"]]]
      (is (= 7 
             (count (distinct-replacements molecule replacements)))))))
