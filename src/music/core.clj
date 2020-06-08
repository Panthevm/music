(ns music.core
  (:import [jm.music.data Note Phrase Part Score]
           [jm.util Play View]))

(def settings
  {:bpm 165})

(def hat   {:notes [[100 1] [100 1] [100 1] [100 1] [100 1] [100 1] [100 1] [100 1] [100 1] [100 1] [100 1] [100 1] [100 1] [100 1] [100 1] [100 1] [100 1] [100 1] [100 1] [100 1]] :channel 0 :type  124})
(def cello {:notes [[50  2        ] [53  2        ] [52  1] [50  2        ] [53  2        ] [52  1] [50  2        ] [53  2        ] [52  1] [50  2        ] [53  2        ] [52  1]] :channel 1 :type  42})

(defn make-note [[pitch rhythm]]
  (Note. (int pitch) (float rhythm)))

(defn make-phrase [notes]
  (let [phrase (Phrase.)]
    (doseq [note notes]
      (.addNote phrase (make-note note)))
    phrase))

(defn make-part [{:keys [name type channel notes]}]
  (let [part (Part. name type channel)]
    (.addPhrase part (make-phrase notes))
    part))

(defn make-pattern [patterns]
  (let [score (Score.)]
    (doseq [pattern patterns]
      (.addPart score (make-part pattern)))
    score))


(let [s (make-pattern [hat cello])]
  (.setTempo s (:bpm settings))
  (Play/midi s))
