(ns aoc.day20
  (:require [clojure.math.combinatorics :as c]))

(defn divides? [n d]
  (zero? (rem n d)))

(declare prime? prime-seq)

(defn prime? [n]
  (if (< n 2)
    false
    (every? #(not (divides? n %)) (take-while #(<= (* % %) n) prime-seq))))

(def prime? (memoize prime?))

(def prime-seq
  (concat '(2 3 5 7)
    (filter #(prime? %) (map #(+ (* 2 %) 7) (iterate inc 1)))))

(defn prime-factors [n]
  (loop [test-primes prime-seq
         tmp n
         retval '()]
    (if (= 1 tmp)
      retval
      (if (prime? tmp)
        (concat retval (list tmp))
        (let [p (first test-primes)]
          (if (divides? tmp p)
            (recur test-primes (quot tmp p) (concat retval (list p)))
            (recur (rest test-primes) tmp retval)))))))

(defn all-possible-divisors [n]
  (let [ps (prime-factors n)]
    (->> (range 1 (inc (count ps)))
      (map (fn [d] (c/combinations ps d)))
      (apply concat)
      (map (fn [fs] (apply * fs)))
      (cons 1))))

(defn sum-of-all-divisors [n]
  (apply + (all-possible-divisors n)))

(defn solution [input]
  (->> (iterate inc 1)
    (map (fn [n] [n (sum-of-all-divisors n)]))
    (drop-while (fn [[_ s]] (< s (/ input 10))))
    ffirst))

(defn all-relevant-divisors [n]
  (filter (fn [d] (<= (quot n d) 50)) (all-possible-divisors n)))

(defn sum-of-relevant-divisors [n]
  (apply + (all-relevant-divisors n)))

(defn solution2 [input]
  (->> (iterate inc 1)
    (map (fn [n] [n (sum-of-relevant-divisors n)]))
    (drop-while (fn [[_ s]] (< s (/ input 11))))
    ffirst))

