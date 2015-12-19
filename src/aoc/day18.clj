(ns aoc.day18)

(defn get-light [grid x y]
  (get (get grid y) x))

(defn get-neighbors [grid x y]
  (for [x' (range (- x 1) (+ x 2)) 
        y' (range (- y 1) (+ y 2))
        :when (or (not= x' x) (not= y' y))]
    (get-light grid x' y')))

(defn next-light [grid x y]
  (let [light     (get-light grid x y)
        neighbors (get-neighbors grid x y)
        ons       (count (filter #(= \# %) neighbors))]
    (case light
      \# (if (or (= ons 2) (= ons 3))
            \#
            \.)
      \. (if (= ons 3)
            \#
            \.)
      (println x y))))

(defn next-grid [grid]
  (let [r (count grid)
        c (count (first grid))
        xys (for [y (range r)
                  x (range c)]
              [x y])]
    ; (println r c)
    (->> xys
      (map (fn [[x y]] (next-light grid x y)))
      (partition c)
      (map #(apply str %))
      (into []))))

(defn read-grid [filename]
  (->> filename
    slurp
    clojure.string/split-lines
    (into [])))

(defn solution [filename]
  (let [grid (read-grid filename)]
    (as-> (iterate next-grid grid) $
      (nth $ 100)
      (apply str $)
      (frequencies $)
      (get $ \#))))

(defn turn-corners-on [grid]
  (let [r1  (first grid)
        rs  (rest (take (dec (count grid)) grid))
        rl  (last grid)
        r1' (apply str (concat [\#] 
                               (rest (take (dec (count r1)) r1))
                               [\#]))
        rl' (apply str (concat [\#] 
                               (rest (take (dec (count rl)) rl))
                               [\#]))]
    (apply vector (concat [r1'] rs [rl']))))

(defn next-grid' [grid]
  (-> grid
    next-grid
    turn-corners-on))

(defn solution2 [filename]
  (let [grid  (read-grid filename)
        grid' (turn-corners-on grid)]
    ; (println grid')
    (as-> (iterate next-grid' grid') $
      (nth $ 100)
      (apply str $)
      (frequencies $)
      (get $ \#))))

