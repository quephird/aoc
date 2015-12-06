(ns aoc.day04
  (:require [digest :as d]))

(defn solution [secret-key pattern]
  (->> (range)
    (map (fn [n] [n (d/md5 (str secret-key n))]))
    (drop-while (fn [[n hash]] (not= pattern (subs hash 0 (count pattern)))))
    ffirst))
