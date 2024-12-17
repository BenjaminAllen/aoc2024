(ns aoc2024.helpers
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn get-input [input]
  (slurp (io/resource (str "inputs/" input))))

(defn get-input-lines [input]
  (let [input (get-input input)]
    (str/split-lines input)))

(defn extract-numbers
  ([s]
   (let [ns (re-seq #"\d+" s)]
     (map #(Integer/parseInt %) ns)))
  ([s re]
   (let [ns (re-seq re s)]
     (map #(Integer/parseInt %) ns))))