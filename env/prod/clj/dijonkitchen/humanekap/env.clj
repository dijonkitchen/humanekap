(ns dijonkitchen.humanekap.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[humanekap started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[humanekap has shut down successfully]=-"))
   :middleware identity})
