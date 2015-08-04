(ns leiningen.aot
  (:require [leiningen.compile :refer [compile]])
  (:refer-clojure :exclude [compile]))

(defn- read-file [file]
  (binding [*read-eval* false]
    (read-string (slurp file))))

(defn- require-form? [form]
  (and (seq? form)
       (= (first form) :require)))

(defn- get-ns-require-form [form]
  (rest (first (filter require-form? form))))

(defn- get-required-ns [form]
  (if (or (vector? form) (seq? form))
    (first form)
    form))

(defn find-namespaces [form]
  (map get-required-ns (get-ns-require-form form)))

(defn aot [project & args]
  (let [files (filter (partial re-find #"\.clj(x|c)?$")
                      (map #(.toString %)
                           (apply concat (map #(file-seq (clojure.java.io/file %))
                                              (:source-paths project)))))
        namespaces (vec (sort (into #{} (mapcat (comp find-namespaces read-file) files))))]
    (compile (assoc project :aot namespaces))))
