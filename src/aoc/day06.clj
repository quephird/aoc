(ns aoc.day06)

(defn which-lights [[from-x from-y] [to-x to-y]]
  (for [x (range from-x (inc to-x)) 
        y (range from-y (inc to-y))]
    [x y]))

(defn change-lights [from to curr-lights change-fn]
  (let [lights-to-change (which-lights from to)]
    (reduce change-fn curr-lights lights-to-change)))

(defn turn-on [from to curr-lights]
  (change-lights from to curr-lights (fn [acc light] (assoc-in acc [light] true))))

(defn turn-off [from to curr-lights]
  (change-lights from to curr-lights (fn [acc light] (assoc-in acc [light] false))))

(defn toggle [from to curr-lights]
  (change-lights from to curr-lights (fn [acc light] (update-in acc [light] not))))

(defn parse-record [record]
  (let [regexp #"(.*) (\d+),(\d+) through (\d+),(\d+)"
        [[_ command & xs-and-ys]] (re-seq regexp record)
        change-fn  (case command
                      "turn on"  turn-on
                      "turn off" turn-off
                      "toggle"   toggle)
        [from-x from-y to-x to-y] (map #(Integer/parseInt %) xs-and-ys)]
    [change-fn [from-x from-y] [to-x to-y]]))

(defn solution [filename]
  (let [lights (into {}
                  (for [x (range 1000) 
                        y (range 1000)]
                    [[x y] false]))]
    (->> filename
      slurp
      clojure.string/split-lines
      (map parse-record)
      (reduce (fn [acc [change-fn from to]] (change-fn from to acc)) lights)
      (filter (fn [[_ state]] (true? state)))
      count)))

(defn turn-up [from to curr-lights]
  (change-lights from to curr-lights (fn [acc light] (update-in acc [light] inc))))

(defn turn-down [from to curr-lights]
  (change-lights from to curr-lights (fn [acc light] (update-in acc [light] (fn [n] (max (dec n) 0))))))

(defn turn-up-by-two [from to curr-lights]
  (change-lights from to curr-lights (fn [acc light] (update-in acc [light] + 2))))

(defn parse-record' [record]
  (let [regexp #"(.*) (\d+),(\d+) through (\d+),(\d+)"
        [[_ command & xs-and-ys]] (re-seq regexp record)
        change-fn  (case command
                      "turn on"  turn-up
                      "turn off" turn-down
                      "toggle"   turn-up-by-two)
        [from-x from-y to-x to-y] (map #(Integer/parseInt %) xs-and-ys)]
    [change-fn [from-x from-y] [to-x to-y]]))

(defn solution2 [filename]
  (let [lights (into {}
                  (for [x (range 1000) 
                        y (range 1000)]
                    [[x y] 0]))]
    (->> filename
      slurp
      clojure.string/split-lines
      (map parse-record')
      (reduce (fn [acc [change-fn from to]] (change-fn from to acc)) lights)
      (reduce (fn [acc [_ brightness]] (+ acc brightness)) 0))))


