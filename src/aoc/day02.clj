(ns aoc.day02)

(defn wrapping-paper-fn [l w h]
  (+ (* 2 l w)
     (* 2 w h)
     (* 2 l h)
     (min (* l w) (* w h) (* l h))))

(defn ribbon-fn [l w h]  
  (+ (* l w h)
     (min (+ (* 2 l) (* 2 w))
          (+ (* 2 w) (* 2 h))
          (+ (* 2 l) (* 2 h)))))

(defn process [input func]
  (let [lines    (clojure.string/split-lines input)
        records  (map (fn [line] (clojure.string/split line #"x")) lines)
        data     (map (fn [record] (map #(Integer/parseInt %) record)) records)]
    (reduce (fn [acc [l w h]] (+ acc (func l w h))) 0 data)))

(defn solution [filename]
  (-> filename
    slurp
    (process wrapping-paper-fn)))

(defn solution2 [filename]
  (-> filename
    slurp
    (process ribbon-fn)))
