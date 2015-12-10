(ns aoc.day09
  (:require [clojure.math.combinatorics :as c]))

; Read file
; Read each line
;   tokenize into city one and city two and their distances
;   add to hashmap twice keyed by [city1, city2] and [city2, city1]
; Generate list of distinct cities
; Generate all combinations 
; Compute all total distances 
; Find one with smallest sum

(defn load-file-into-lines [filename]
  (-> filename
    slurp
    clojure.string/split-lines))
  
(defn parse-lines [input]
  (map (fn [line]
         (let [[_ city1 city2 distance] (re-find #"(.*) to (.*) = (\d+)" line)]
           [city1 city2 (Integer/parseInt distance)])) input))

(defn all-distances [all-records]
  (reduce (fn [acc [city1 city2 distance]]
            (-> acc
              (assoc-in [[city1 city2]] distance)
              (assoc-in [[city2 city1]] distance))) {} all-records))

(defn all-cities [all-records]
  (reduce (fn [acc [city1 city2 distance]]
            (-> acc
              (conj city1)
              (conj city2))) #{} all-records))

(defn all-permutations [cities]
  (c/permutations cities))

(defn total-distance [distances permutation]
  (let [trips (map vector permutation (rest permutation))] 
    (reduce (fn [acc [c1 c2]]
              (+ acc (get-in distances [[c1 c2]]))) 0 trips)))

(defn all-total-distances [distances permutations]
  (map (fn [p] [p (total-distance distances p)]) permutations))

(defn solution [filename]
  (let [records (->> filename
                  load-file-into-lines
                  parse-lines)
        distances (all-distances records)
        cities    (all-cities records)]
    (->> cities
      all-permutations
      (all-total-distances distances)
      (reduce (fn [[min-p min-d :as acc] [p d]]
                (if (< d min-d)
                  [p d]
                  acc)) [[] 1000000]))))

(defn solution2 [filename]
  (let [records (->> filename
                  load-file-into-lines
                  parse-lines)
        distances (all-distances records)
        cities    (all-cities records)]
    (->> cities
      all-permutations
      (all-total-distances distances)
      (reduce (fn [[max-p max-d :as acc] [p d]]
                (if (> d max-d)
                  [p d]
                  acc)) [[] 0]))))
    
