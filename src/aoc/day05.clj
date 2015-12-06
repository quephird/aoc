(ns aoc.day05)

(defn has-at-least-three-vowels [string]
  (<= 3 (- (count string)
           (count (clojure.string/replace string #"[aeiou]" "")))))

(defn has-letter-that-appears-twice-in-row [string]
  (not (nil? (re-find #"([a-z])(\1+)" string))))

(defn doesnt-have-bad-strings [string]
  (nil? (re-find #"(ab|cd|pq|xy)" string)))

(defn nice? [string]
  (and (has-at-least-three-vowels string)
       (has-letter-that-appears-twice-in-row string)
       (doesnt-have-bad-strings string)))

(defn solution [filename]
  (->> filename
    slurp
    clojure.string/split-lines
    (filter nice?)
    count))

(defn has-two-letters-appearing-twice-not-overlapping [string]
  (not (nil? (re-find #"([a-z][a-z]).*(\1)" string))))
  
(defn has-one-letter-which-repeats-with-one-letter-between [string]
  (not (nil? (re-find #"([a-z])[a-z](\1)" string))))
  
(defn nice?' [string]
  (and (has-two-letters-appearing-twice-not-overlapping string)
       (has-one-letter-which-repeats-with-one-letter-between string)))

(defn solution2 [filename]
  (->> filename
    slurp
    clojure.string/split-lines
    (filter nice?')
    count))
