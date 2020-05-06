(ns user
  "Userspace functions you can run by default in your local REPL."
  (:require
   [dijonkitchen.humanekap.config :refer [env]]
    [clojure.pprint]
    [clojure.spec.alpha :as s]
    [expound.alpha :as expound]
    [mount.core :as mount]
    [dijonkitchen.humanekap.figwheel :refer [start-fw stop-fw cljs]]
    [dijonkitchen.humanekap.core :refer [start-app]]
    [dijonkitchen.humanekap.db.core]
    [conman.core :as conman]
    [luminus-migrations.core :as migrations]))

(alter-var-root #'s/*explain-out* (constantly expound/printer))

(add-tap (bound-fn* clojure.pprint/pprint))

(defn start
  "Starts application.
  You'll usually want to run this on startup."
  []
  (mount/start-without #'dijonkitchen.humanekap.core/repl-server))

(defn stop
  "Stops application."
  []
  (mount/stop-except #'dijonkitchen.humanekap.core/repl-server))

(defn restart
  "Restarts application."
  []
  (stop)
  (start))

(defn restart-db
  "Restarts database."
  []
  (mount/stop #'dijonkitchen.humanekap.db.core/*db*)
  (mount/start #'dijonkitchen.humanekap.db.core/*db*)
  (binding [*ns* 'dijonkitchen.humanekap.db.core]
    (conman/bind-connection dijonkitchen.humanekap.db.core/*db* "sql/queries.sql")))

(defn reset-db
  "Resets database."
  []
  (migrations/migrate ["reset"] (select-keys env [:database-url])))

(defn migrate
  "Migrates database up for all outstanding migrations."
  []
  (migrations/migrate ["migrate"] (select-keys env [:database-url])))

(defn rollback
  "Rollback latest database migration."
  []
  (migrations/migrate ["rollback"] (select-keys env [:database-url])))

(defn create-migration
  "Create a new up and down migration file with a generated timestamp and `name`."
  [name]
  (migrations/create name (select-keys env [:database-url])))


