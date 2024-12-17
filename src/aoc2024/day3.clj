(ns aoc2024.day3
  (:require [aoc2024.helpers :refer [get-input extract-numbers]]
            [clojure.string :as str]))

(def input (get-input "day3.txt"))

(defn extract-muls [input]
  (let [re #"mul\(\d+,\d+\)"
        muls (re-seq re input)]
    muls))

(defn calculate-mul [mul]
  (let [[x y] (extract-numbers mul)]
    (* x y)))

(defn remove-disabled-code [input]
  (let [input (str/replace input #"don't\(\)" "|")
        input (str/replace input #"do\(\)" "£")
        reducer (fn [acc c]
                  (case c
                    \| (assoc acc :on nil)
                    \£ (assoc acc :on true)
                    (if (acc :on)
                      (update acc :input #(conj % c))
                      acc)))]
    (as-> (reduce reducer {:input [] :on true} input) input
      (apply str (:input input)))))

(defn part-one [input]
  (->> (extract-muls input)
       (map calculate-mul)
       (apply +)))

(defn part-two [input]
  (->> (remove-disabled-code input)
       part-one))

(comment
  (part-one input)
  ;;=> 170068701

  (part-two input)
  ;;=> 78683433

  (def example "xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))")
  )