(ns aoc.day16)

; Load file
; Parse it into a data structure
;   * hashmap of Sue#'s and there respective counts
; Iterate through list of known counts
;   * Go through and "mark off" all Sue's whose counts of this item are wrong
; Whatever one that is left that is still unchecked is the correct Sue

(def readings
  [[:children 3]
   [:cats 7]
   [:samoyeds 2]
   [:pomeranians 3]
   [:akitas 0]
   [:vizslas 0]
   [:goldfish 5]
   [:trees 3]
   [:cars 2]
   [:perfumes 1]])

(defn parse-record [line]
  (let [pattern #"Sue (.*): (.*): (\d+), (.*): (\d+), (.*): (\d+)"
        [_ n i1 c1 i2 c2 i3 c3] (re-find pattern line)
        sue#    (Integer/parseInt n)
        items   (map keyword [i1 i2 i3])
        counts  (map #(Integer/parseInt %) [c1 c2 c3])
        data    (into {} (map vector items counts))]
    [sue# data]))

(defn get-data [filename]
  (->> filename
    slurp
    clojure.string/split-lines
    (map parse-record)
    (into {})))

(defn check-sue [[_ data] [item count]]
  (let [count' (get data item)]
    (or  (nil? count')
         (= count count'))))

(defn check-all-sues [check-fn sues reading]
  (->> sues
    (filter (fn [[n _ :as sue]] (check-fn sue reading)))))

(defn solution [filename readings]
  (as-> filename $
    (get-data $)
    (reduce (fn [acc reading] (check-all-sues check-sue acc reading)) $ readings)))

(defn check-sue' [[_ data] [item count]]
  (let [count' (get data item)]
    (cond 
      (nil? count') true
    (or (= item :cats) (= item :trees))
      (> count' count)
    (or (= item :pomeranians) (= item :goldfish))
      (< count' count)
    :else
      (= count' count))))

(defn solution2 [filename readings]
  (as-> filename $
    (get-data $)
    (reduce (fn [acc reading] (check-all-sues check-sue' acc reading)) $ readings)))

    
