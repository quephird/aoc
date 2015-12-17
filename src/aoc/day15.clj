(ns aoc.day15)

(defn parse-record [line]
  (let [pattern #"(.*): (.*) (.*), (.*) (.*), (.*) (.*), (.*) (.*), (.*) (.*)"
        [_ i n1 v1 n2 v2 n3 v3 n4 v4 n5 v5] (re-find pattern line)
        names      (map keyword [n1 n2 n3 n4 n5])
        values     (map #(Integer/parseInt %) [v1 v2 v3 v4 v5])
        properties (into {} (map vector names values))]
    [i properties]))

(defn get-ingredient-data [filename]
  (->> filename
    slurp
    clojure.string/split-lines
    (map parse-record)))

(defn score [ingredient-data proportions]
  (let [capacities   (map #(:capacity (second %)) ingredient-data)
        durabilities (map #(:durability (second %)) ingredient-data)
        flavors      (map #(:flavor (second %)) ingredient-data)
        textures     (map #(:texture (second %)) ingredient-data)]
    (->> [capacities durabilities flavors textures]
      (map (fn [ps] 
             (let [temp (reduce + 0 (map * ps proportions))]
               (if (< temp 0) 0 temp))))
      (apply *))))

(def all-proportions
  (for [c (range 101) 
        d (range 101) 
        f (range 101) 
        t (range 101) :when (= 100 (+ c d f t))]
    [c d f t]))

(defn all-scores [all-proportions ingredient-data]
  (map (fn [p] (score ingredient-data p)) all-proportions))

(defn solution [filename]
  (->> filename
    get-ingredient-data
    (all-scores all-proportions)
    (apply max)))
    
(defn score-and-calories [ingredient-data proportions]
  (let [calories     (map #(:calories (second %)) ingredient-data)
        score'       (score ingredient-data proportions)
        calories'     (reduce + 0 (map * calories proportions))]
    [score' calories']))

(defn all-scores-and-calories [all-proportions ingredient-data]
  (map (fn [p] (score-and-calories ingredient-data p)) all-proportions))
    
(defn solution2 [filename]
  (->> filename
    get-ingredient-data
    (all-scores-and-calories all-proportions)
    (filter (fn [[_ c]] (= c 500)))
    (apply max-key (fn [[s _]] s))
    first))
