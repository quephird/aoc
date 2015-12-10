(ns aoc.day07)

(defn numeric? [string]
  (try (number? (Integer/parseInt string))
    (catch Exception e false)))

(defn parse-single-signal [line]
  (let [[_ in1 out :as match] (re-find #"^(.*) -> (.*)" line)]
    (if match
      {:inputs [(if (numeric? in1) (Integer/parseInt in1) (keyword in1))] :output (keyword out)})))

(defn parse-not [line]
  (let [[_ in out :as match] (re-find #"NOT (.*) -> (.*)" line)]
    (if match
      {:inputs [(keyword in)] :op :not :output (keyword out)})))
      
(defn parse-and [line]
  (let [[_ in1 in2 out :as match] (re-find #"(.*) AND (.*) -> (.*)" line)]
    (if match
      {:inputs [(if (numeric? in1) (Integer/parseInt in1) (keyword in1)) (keyword in2)] :op :and :output (keyword out)})))

(defn parse-or [line]
  (let [[_ in1 in2 out :as match] (re-find #"(.*) OR (.*) -> (.*)" line)]
    (if match
      {:inputs [(if (numeric? in1) (Integer/parseInt in1) (keyword in1)) (keyword in2)] :op :or :output (keyword out)})))

(defn parse-lshift [line]
  (let [[_ in1 in2 out :as match] (re-find #"(.*) LSHIFT (\d+) -> (.*)" line)]
    (if match
      {:inputs [(keyword in1) (Integer/parseInt in2)] :op :lshift :output (keyword out)})))

(defn parse-rshift [line]
  (let [[_ in1 in2 out :as match] (re-find #"(.*) RSHIFT (\d+) -> (.*)" line)]
    (if match
      {:inputs [(keyword in1) (Integer/parseInt in2)] :op :rshift :output (keyword out)})))

(defn parse-line [line]
  (let [matchers [parse-not
                  parse-and
                  parse-or
                  parse-lshift
                  parse-rshift
                  parse-single-signal]]
                                  
    (->> matchers
      (map (fn [matcher] (matcher line)))
      (filter (fn [result] (not (nil? result))))
      first)))

(defn parse-lines [input]
  (map parse-line (clojure.string/split-lines input)))

(defn build-circuit [parts]
  (reduce (fn [acc {:keys [output inputs op]}] 
            (assoc-in acc [output] {:inputs inputs :op op})) {} parts))

(def compute-value 
  (memoize 
    (fn [output circuit] 
      (let [{:keys [inputs op]} (output circuit)
            [in1 in2]           inputs]
        (case op
          :lshift (bit-shift-left (compute-value in1 circuit) in2)
          :rshift (bit-shift-right (compute-value in1 circuit) in2)
          :not    (bit-and-not 65535 (compute-value in1 circuit))
          :and    (bit-and (if (keyword? in1) 
                             (compute-value in1 circuit) 
                             in1)
                           (compute-value in2 circuit))
          :or     (bit-or (if (keyword? in1) 
                            (compute-value in1 circuit) 
                            in1)
                          (compute-value in2 circuit))
          (if (keyword? in1) 
            (compute-value in1 circuit) 
            in1))))))

(defn run-circuit [circuit]
  (reduce (fn [acc [output _]]
            (assoc-in acc [output] 
              (compute-value output circuit))) {} circuit)) 

(defn solution [filename output]
  (->> filename
    slurp
    parse-lines
    build-circuit
    (compute-value output)))

(defn override-input [output new-input circuit]
  (assoc-in circuit [output :inputs] new-input)) 

(defn solution2 [filename output]
  (->> filename
    slurp
    parse-lines
    build-circuit
    (override-input :b [(solution filename output)])
    (compute-value output)))
