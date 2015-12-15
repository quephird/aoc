(ns aoc.day12
  (:require [cheshire.core :as c]))

(defn extract-numbers [input]
  (cond 
    (number? input) input 
    (empty? input) nil
    (map? input) (extract-numbers (vals input))
    (or (list? input) (vector? input) (seq? input))
      (let [[x & xs] (filter (fn [i] 
                               (not (or (string? i)
                                        (symbol? i)
                                        (keyword? i)))) input)]
        (cons (extract-numbers x) (extract-numbers xs)))
    :else nil))

(defn solution [filename]
  (->> filename
    slurp
    c/parse-string
    extract-numbers
    flatten
    (filter #(not (nil? %)))
    (reduce + 0)))

(defn extract-numbers' [input]
  (cond 
    (number? input) input 
    (empty? input) nil
    (map? input) 
      (if (some (fn [[k v]] (= "red" v)) input)
        nil
        (extract-numbers' (vals input)))
    (or (list? input) (vector? input) (seq? input))
      (let [[x & xs] (filter (fn [i] 
                               (not (or (string? i)
                                        (symbol? i)
                                        (keyword? i)))) input)]
        (cons (extract-numbers' x) (extract-numbers' xs)))
    :else nil))

(defn solution2 [filename]
  (->> filename
    slurp
    c/parse-string
    extract-numbers'
    flatten
    (filter #(not (nil? %)))
    (reduce + 0)))
