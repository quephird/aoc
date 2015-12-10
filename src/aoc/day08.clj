(ns aoc.day08)

(defn quote? [c]
  (= \" c))

(defn backslash? [c1 c2]
  (= \\ c1 c2))

(defn escaped-quote? [c1 c2]
  (and (= \\ c1) (= \" c2)))

(defn hexadecimal-literal [c1 c2]
  (and (= \\ c1) (= \x c2)))  

(defn compute-sums-iter [[total-chars actual-chars :as acc] [c1 & [c2 & two-chars-in :as one-char-in]]]
  (cond 
    (nil? c1) acc
    (quote? c1) (recur [(inc total-chars) actual-chars] one-char-in)
    (backslash? c1 c2) (recur [(+ total-chars 2) (inc actual-chars)] two-chars-in)
    (escaped-quote? c1 c2) (recur [(+ total-chars 2) (inc actual-chars)] two-chars-in)
    (hexadecimal-literal c1 c2) (recur [(+ total-chars 4) (inc actual-chars)] (drop 2 two-chars-in))
    :else (recur [(inc total-chars) (inc actual-chars)] one-char-in)))

(defn compute-sums [line]
  (compute-sums-iter [0 0] line))

(defn compute-sums-iter' [[total-chars actual-chars :as acc] [c1 & [c2 & two-chars-in :as one-char-in]]]
  (cond 
    (nil? c1) acc
    (quote? c1) (recur [(+ total-chars 3) (inc actual-chars)] one-char-in)
    (backslash? c1 c2) (recur [(+ total-chars 4) (+ actual-chars 2)] two-chars-in)
    (escaped-quote? c1 c2) (recur [(+ total-chars 4) (+ actual-chars 2)] two-chars-in)
    (hexadecimal-literal c1 c2) (recur [(+ total-chars 5) (+ actual-chars 4)] (drop 2 two-chars-in))
    :else (recur [(inc total-chars) (inc actual-chars)] one-char-in)))

(defn compute-sums' [line]
  (compute-sums-iter' [0 0] line))

(defn solution-helper [filename compute-fn]
  (let [rdr (clojure.java.io/reader filename)
        [raw-chars actual-chars] (->> (line-seq rdr)
                                   (map compute-fn)
                                   (reduce (fn [totals partial-sums] (map + totals partial-sums)) [0 0]))]
    (.close rdr)
    (- raw-chars actual-chars)))             
    
(defn solution [filename]
  (solution-helper filename compute-sums))

(defn solution2 [filename]
  (solution-helper filename compute-sums'))

