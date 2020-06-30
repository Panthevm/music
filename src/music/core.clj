(ns music.core
  (:import [jm.music.data Note Phrase Part Score]
           [jm.util Play View Write]))

(def settings
  {:bpm 165})

(def main {:notes [[41 1]
                   [41 1]
                   [46 1]
                   [44 1]

                   [41 1]
                   [41 1]
                   [40 1]
                   [42 1]

                   [41 1]
                   [41 1]
                   [46 1]
                   [44 1]

                   [41 1]
                   [41 1]
                   [40 1]
                   [42 1]

                   [41 1]
                   [41 1]
                   [46 1]
                   [44 1]

                   [41 1]
                   [41 1]
                   [40 1]
                   [42 1]

                   [41 1]
                   [41 1]
                   [46 1]
                   [44 1]

                   [44 1]
                   [41 1]
                   [42 1]
                   [42 1]] :channel 1 :type 27})

(def snare {:notes [[]
                    []
                    []
                    []

                    [70 1]
                    []
                    []
                    []

                    []
                    []
                    []
                    []

                    [70 1]
                    []
                    []
                    []

                    []
                    []
                    []
                    []

                    [70 1]
                    []
                    []
                    []

                    []
                    []
                    []
                    []

                    [70 1]
                    []
                    []
                    []]  :channel 2 :type 124})

(def kick {:notes [[40 0.2] [0.8]
                   []
                   []
                   []

                   []
                   []
                   []
                   []

                   []
                   []
                   [40 0.2] [0.8]
                   []

                   []
                   [40 0.2] [0.8]
                   []
                   []

                   [40 0.2] [0.8]
                   []
                   []
                   []

                   []
                   []
                   []
                   []

                   []
                   []
                   [40 0.2] [0.8]
                   []

                   []
                   []
                   []
                   []]  :channel 3 :type 113})

(defn make-note [[pitch rhythm :as note]]
  (cond
    (and pitch rhythm) (Note. (int pitch) (float rhythm) 120)
    pitch              (Note. 0 pitch 0 0.0)
    :else              (Note. 0 1.0 0 0.0)))

(defn make-phrase [notes]
  (let [phrase (Phrase.)]
    (doseq [note notes]
      (.addNote phrase (make-note note)))
    phrase))

(defn make-part [{:keys [name type channel notes]}]
  (let [part (Part. name type channel)]
    (.addPhrase part (make-phrase notes))
    part))

; 9 13 31 32 55 58 
(comment
  37
  126
  [3 4 6 24 27 33 38 47 52 65 113 115 119 122 124 126]
  )

(defn repeat [pattern]
  (update pattern :notes into (:notes pattern)))

(defn make-pattern [patterns]
  (let [score (Score.)]
    (doseq [pattern patterns]
      (.addPart score (make-part pattern)))
    score))

(defn play []
  (let [s (make-pattern [(repeat main) (repeat snare) (repeat kick)])]
    (.setTempo s (:bpm settings))
    (Play/midi s)))

(doall (play))



(comment
  ;default
  [[] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] [] []])
