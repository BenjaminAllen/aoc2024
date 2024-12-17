(ns aoc2024.day2
  (:require [aoc2024.helpers :refer [get-input-lines extract-numbers]]))

(def input (get-input-lines "day2.txt"))


(defn safe? [report]
  (let [reducer (fn [x y]
                  (let [valid (<= 1 (abs (- x y)) 3)]
                    (if valid
                      y
                      (reduced nil))))]
    (and (or (apply < report)
             (apply > report))
         (reduce reducer report))))

(defn dampened-safe? [report]
  (let [report-length (count report)]
    report-length))

(defn part-one [input]
  (let [reports (map extract-numbers input)]
    (count (filter safe? reports))))

(defn part-two [input]
  (let [reports (map extract-numbers input)]))
(comment
  (part-one input)
  ;;=> 670
  
  
(def test-report "17 19 20 21 23 24 29 27")
  (dampened-safe? (extract-numbers test-report))
  )

 