(ns aoc2024.day4
  (:require [aoc2024.helpers :refer [get-input-lines]]))

(def grid (map #(apply vector %) (get-input-lines "day4.txt")))

(def xmas [\X \M \A \S])

(def directions {:up         {:condition (fn [_ y _ _] (<= 3 y))
                              :x-movement 0
                              :y-movement -1}
                 :down       {:condition (fn [_ y _ col-count] (<= y (- col-count 4)))
                              :x-movement 0
                              :y-movement 1}
                 :left       {:condition (fn [x _ _ _] (<= 3 x))
                              :x-movement -1
                              :y-movement 0}
                 :right      {:condition (fn [x _ row-count _] (<= x (- row-count 4)))
                              :x-movement 1
                              :y-movement 0}
                 :up-right   {:condition (fn [x y row-count _]
                                           (and (<= 3 y)
                                                (<= x (- row-count 4))))
                              :x-movement 1
                              :y-movement -1}
                 :up-left    {:condition (fn [x y _ _]
                                           (and (<= 3 x)
                                                (<= 3 y)))
                              :x-movement -1
                              :y-movement -1}
                 :down-right {:condition (fn [x y row-count col-count]
                                           (and (<= y (- col-count 4))
                                                (<= x (- row-count 4))))
                              :x-movement 1
                              :y-movement 1}
                 :down-left  {:condition (fn [x y _ col-count]
                                           (and (<= y (- col-count 4))
                                                (<= 3 x)))
                              :x-movement -1
                              :y-movement 1}})

(defn get-at [grid x y]
  (nth (nth grid y) x))

(defn coords [grid]
  (let [width (count (first grid))
        height (count grid)]
    (for [x (range 0 width)
          y (range 0 height)]
      (vector x y))))

(defn check-direction [grid x y direction]
  (let [row-count (count (first grid))
        col-count (count grid)
        {condition :condition
         x-movement :x-movement
         y-movement :y-movement} (directions direction)]
    (if (condition x y row-count col-count)
      (= [(get-at grid x y)
          (get-at grid (+ x x-movement) (+ y y-movement))
          (get-at grid (+ x (* 2 x-movement)) (+ y (* 2 y-movement)))
          (get-at grid (+ x (* 3 x-movement)) (+ y (* 3 y-movement)))]
         xmas)
      nil)))

(defn check-position [grid x y]
  (if (= (get-at grid x y) \X)
    (let [results (map #(check-direction grid x y %) (keys directions))]
      (count (filter true? results)))
    0))

(defn part-one [grid]
  (let [coords (coords grid)]
    (apply + (map (fn [coord]
                    (let [[x y] coord]
                      (check-position grid x y))) coords))))

(comment
  (part-one grid)
  
  
  )