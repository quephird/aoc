(ns aoc.day14)

(defn distance-traveled [time {:keys [speed max-flying-time min-rest-time] :as reindeer}]
  (let [cycle (+ max-flying-time min-rest-time)]
    (* speed (+ (* max-flying-time (quot time cycle))      ; Completed cycles
                (min (mod time cycle) max-flying-time))))) ; Seconds into current cycle

(defn parse-record [line]
  (let [pattern     #"(.*) can fly (\d+) km/s for (\d+) seconds, but then must rest for (\d+) seconds."
        [_ n s f r] (re-find pattern line)
        [s f r]     (map #(Integer/parseInt %) [s f r])]
    {:name n :stats {:speed s :max-flying-time f :min-rest-time r}}))

(defn get-reindeer [filename]
  (->> filename
    slurp
    clojure.string/split-lines
    (map parse-record)))

(defn solution [filename]
  (->> filename
    get-reindeer
    (map (fn [{:keys [name stats]}] [name (distance-traveled 2503 stats)]))
    (into {})
    (apply max-key val)))

(defn leaders [time raindeer]
  (let [distances (->> raindeer
                    (map (fn [{:keys [name stats]}] [name (distance-traveled time stats)]))
                    (into {}))
        max-d     (apply max (vals distances))]
    (->> distances
      (filter (fn [[name d]] (= d max-d)))
      keys)))

(defn solution2 [filename]
  (let [reindeer (get-reindeer filename)
        init-acc (into {} (map vector (map :name reindeer) (repeat 0)))
        scores   (reduce (fn [acc n]
                           (let [ls         (leaders n reindeer)
                                 new-points (into {} (map vector ls (repeat 1)))]
                             (merge-with + acc new-points))) init-acc (range 1 2504))]
    (->> scores
      (apply max-key val))))

