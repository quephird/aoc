(ns aoc.day11)

; Passwords must include one increasing straight of at least three letters, like abc, bcd, cde, and so on, up to xyz. They cannot skip letters; abd doesn't count.
; Passwords may not contain the letters i, o, or l, as these letters can be mistaken for other characters and are therefore confusing.
; Passwords must contain at least two different, non-overlapping pairs of letters, like aa, bb, or zz.

(defn passes-rule2? [password]
  (nil? (re-find #"(i|o|l)" password)))

(defn passes-rule3? [password]
  (nil? (re-find #"([a-z])\1{2}+)" password)))
