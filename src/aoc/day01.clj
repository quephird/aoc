(ns aoc.day01)

(defn answer [filename]
  (let [input (slurp filename)]
    (reduce (fn [a c]
              (+ a (case c 
                      \( 1
                      \) -1 
                      0))) 0 input)))

(defn answer2 [filename]
  (->> filename
    slurp 
    (reduce (fn [[floor idx] c]
              (let [new-floor (+ floor (case c \( 1 \) -1 0))] 
                (if (= new-floor -1)
                  (reduced [new-floor idx])
                  [new-floor (inc idx)]))) [0 1])))
