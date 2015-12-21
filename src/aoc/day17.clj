(ns aoc.day17)

; Put container sizes in descending order
; For each item in the list
;   Subtract size from total capacity
;   recurse for that difference and remaining list

(defn container-combos [total-capacity [c & cs]]
  (cond
    (nil? c) ()
    (> c total-capacity) (container-combos total-capacity cs)
    (= c total-capacity) [c]
    :else (map (fn [c'] (cons c (container-combos (- total-capacity c) cs))))
