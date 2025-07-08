import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import Kayit from "./Kayit.jsx";
import {
  BrowserRouter,
  Route,
  Switch,
} from "react-router-dom/cjs/react-router-dom.min.js";
import Giris from "./Giris.jsx";
import Anasayfa from "./Anasayfa.jsx";

createRoot(document.getElementById("root")).render(
  <BrowserRouter>
    <Switch>
      <Route exact path="/">
        <Kayit />
      </Route>
      <Route path="/giris">
        <Giris />
      </Route>
      <Route path="/anasayfa">
        <Anasayfa />
      </Route>
    </Switch>
  </BrowserRouter>
);
