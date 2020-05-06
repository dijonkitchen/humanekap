(ns dijonkitchen.humanekap.app
  (:require [dijonkitchen.humanekap.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init! false)
