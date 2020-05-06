(ns dijonkitchen.humanekap.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [dijonkitchen.humanekap.core-test]))

(doo-tests 'dijonkitchen.humanekap.core-test)

