(ns aoc.day19)

(defn parse-record [line]
  (let [pattern     #"(.*) => (.*)"
        [_ elt rep] (re-find pattern line)]
    [elt rep]))

(defn parse-replacements [lines]
  (->> lines
    clojure.string/split-lines
    (map parse-record)
    (into [])))

(defn get-data [filename]
  (let [[records molecule] (-> filename
                             slurp
                             (clojure.string/split #"\n\n"))
        replacements  (parse-replacements records)]
    [replacements (trim molecule)]))

(defn replace-once [molecule elt rep]
  (loop [molecules [] 
         curr-idx  0]
    (let [idx (.indexOf molecule elt curr-idx)]
      (cond 
        (= -1 idx)
          molecules
        :else
          (let [f   (subs molecule 0 idx)
                r   (subs molecule idx)
                new-molecule (str f (clojure.string/replace-first r elt rep))]
            (recur (conj molecules new-molecule)
                   (+ idx (count elt))))))))

(defn distinct-replacements [molecule replacements]
  (->> replacements
    (map (fn [[e r]] (replace-once molecule e r)))
    (apply concat)
    (map (fn [m] (apply str m)))
    (into #{})))

(defn solution [filename]
  (let [[replacements molecule] (get-data filename)]
    (count (distinct-replacements molecule replacements))))

(defn solution2 [filename]
  (let [[replacements molecule] (get-data filename)]
    (replace-once molecule "PB" "Ca")))