(ns car-race.core)

(defn car-make []
  ["-"])

(defn car-add-step [car]
  (conj car "-"))

(defn car-maybe-move [car]
  (if (>= 5 (rand-int 10))
    (car-add-step car)
    car))

(defn car->str [car]
  (clojure.string/join " " car))

(defn board-make []
  [(car-make) (car-make) (car-make)])

(defn board-show [state]
  (doseq [car state]
    (println (car->str car))))

(defn board-next-step [state]
  (doall (map car-maybe-move state)))

(defn start-game [state]
  (loop [step 2
         step-state state]
    (let [next-state (board-next-step step-state)]
      (board-show next-state)
      (println)
      (if (> step 4)
        next-state
        (recur (inc step) next-state)))))
    
(comment
  (car-maybe-move (car-make))
  (board-show (board-make))
  (board-show '(["-" "-" "-"] ["-"] ["-"]))
  (-> (board-make)
      start-game))