(native-header "Keypad.h")

(defmacro device [keys row-pins col-pins]
  (let [keys (map (fn [s] (map #(str "'" % "'") s)) keys)
        row-pin-cnt (count row-pins)
        col-pin-cnt (count col-pins)
        to-arr (fn [s] (str "{" (apply str (interpose \, s)) "}"))
        km-sym (gensym)
        km (str "char " km-sym "[" (count row-pins) "][" (count col-pins) "]="
                (to-arr (map to-arr keys)) ";")
        rp-sym (gensym)
        row-pins (str "byte " rp-sym "[" (count row-pins) "]=" (to-arr row-pins) ";")
        cp-sym (gensym)
        col-pins (str "byte " cp-sym "[" (count col-pins) "]=" (to-arr col-pins) ";")
        km-obj-sym (gensym)
        km-obj (str "Keypad " km-obj-sym " = " "Keypad( makeKeymap(" km-sym ")," rp-sym "," cp-sym "," row-pin-cnt "," col-pin-cnt ");")]
    `(~'do
       (~'native-declare ~km)
       (~'native-declare ~row-pins)
       (~'native-declare ~col-pins)
       (~'native-declare ~km-obj)
       (~'cxx ~(str "__result = obj<Pointer>(&" km-obj-sym ");")))))

(defn read [kp]
  "Keypad *keypad = kp.cast<Pointer>()->pointer<Keypad>();
   char key = keypad->getKey();
   if (key != NO_KEY)
     __result = obj<Number>((number_t)key);")

(defn wait-key [kp]
  "Keypad *keypad = kp.cast<Pointer>()->pointer<Keypad>();
   __result = obj<Number>((number_t)keypad->waitForKey());")
