(ns aoc2024.day1
  (:require [aoc2024.helpers :refer [get-input-lines extract-numbers]]))

(def input (get-input-lines "day1.txt"))

(defn get-lists [input]
  (->> input
       (map extract-numbers)
       (apply map list)))

(defn part-one [input]
  (let [lists (get-lists input)]
    (->> lists
         (map sort)
         (apply interleave)
         (partition 2)
         (map #(abs (apply - %)))
         (apply +))))

(defn part-two [input]
  (let [[list1 list2] (get-lists input)
        freqs (frequencies list2)]
    (->> (map #(* % (get freqs % 0)) list1)
         (apply +))))

(comment
  (part-one input)
  ;;=> 2066446
  
  (part-two input)
  ;;=> 24931009
)

