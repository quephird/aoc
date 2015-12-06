(ns aoc.day03)

(defn move [[x y] dir]
  (case dir
    \^ [x (inc y)]
    \v [x (dec y)]
    \> [(inc x) y]
    \< [(dec x) y]
    [x y]))

(defn deliver-gift [[curr-loc curr-houses] dir]
  (let [new-loc     (move curr-loc dir)
        new-houses  (merge-with + curr-houses {new-loc 1})]
    [new-loc new-houses]))

(defn total-houses-delivered [input]
  (let [init-state [[0 0] {[0 0] 1}]]
    (->> input
      (reduce (fn [acc dir] (deliver-gift acc dir)) init-state)
      second
      keys
      count)))

(defn solution [filename]
  (-> filename
    slurp
    total-houses-delivered))

(defn deliver-gift' [[[curr-santa-loc curr-santa-houses]
                      [curr-robo-loc curr-robo-houses]] [santa-dir robo-dir]]
  (let [new-santa-loc     (move curr-santa-loc santa-dir)
        new-santa-houses  (merge-with + curr-santa-houses {new-santa-loc 1})
        new-robo-loc      (move curr-robo-loc robo-dir)
        new-robo-houses   (merge-with + curr-robo-houses {new-robo-loc 1})]
              
    [[new-santa-loc new-santa-houses] 
     [new-robo-loc new-robo-houses]]))

(defn total-houses-delivered' [input]
  (let [init-state [[[0 0] {[0 0] 1}]
                    [[0 0] {[0 0] 1}]]]
    (->> input
      (partition 2)
      (reduce (fn [acc dir] (deliver-gift' acc dir)) init-state)
      (map second)
      (apply merge)
      (map keys)
      count)))

(defn solution2 [filename]
  (-> filename
    slurp
    total-houses-delivered'))
