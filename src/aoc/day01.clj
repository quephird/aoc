(ns aoc.day01)

(defn answer [filename]
  (let [input (slurp filename)]
    (reduce (fn [a c]
              (+ a (case c 
                      \( 1
                      \) -1 
                      0))) 0 input)))
