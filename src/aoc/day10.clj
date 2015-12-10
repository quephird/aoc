(ns aoc.day10)

(defn look-and-say [string]
  (->> string
    (reduce (fn [[[last-n last-c] & r :as acc] c]
              (if (= c last-c)
                (cons [(inc last-n) last-c] r)
                (cons [1 c] acc))) ())
    reverse
    flatten
    (apply str)))

(defn solution [input n]
  (->> input
    (iterate look-and-say)
    (drop n) 
    first
    count))
