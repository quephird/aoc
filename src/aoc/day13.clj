(ns aoc.day13
  (:require [clojure.math.combinatorics :as c]))

(defn parse-record [line]
  (let [pattern        #"(.*) would (lose|gain) (.*) happiness units by sitting next to (.*)."
        [_ p lg hu nt] (re-find pattern line)
        op             (if (= lg "lose") - +)]
    [[p nt] (op (Integer/parseInt hu))]))

(defn get-happiness-data [filename]
  (->> filename
    slurp
    clojure.string/split-lines
    (map parse-record)
    (into {})))

(defn distinct-people [data]
  (->> data
    keys
    (map first)
    distinct))

(defn happiness [p data]
  (let [p'      (vec p)    ; Do this to preserve order
        seating (partition 3 1 (cons (last p') (conj p' (first p'))))]
    (reduce (fn [acc [l p r]]
              (+ acc (get data [p l] 0)
                     (get data [p r] 0))) 0 seating)))

(defn happiest [data]
  (let [people   (distinct-people data)
        perms    (c/permutations people)]
    (->> perms
      (map (fn [p] [p (happiness p data)]))
      (apply max-key second))))

(defn solution [filename]
  (->> filename
    get-happiness-data
    happiest 
    second))
  
(defn solution2 [filename]
  (let [data       (get-happiness-data filename)
        happiest-p (-> data
                     happiest 
                     first)
        new-ps     (->> (range 0 (count happiest-p))
                     (map (fn [n] (split-at n happiest-p)))
                     (map (fn [[f s]] (concat f ["Me"] s))))]
    (->> new-ps
      (map (fn [p] [p (happiness p data)]))
      (apply max-key second))))
