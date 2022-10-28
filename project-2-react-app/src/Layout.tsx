import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";

/* ===== Components ===== */
import NavigationBar from "./components/NavBar/NavBar";   // Website's Navigation Bar
import Footer from "./components/Footer/Footer";  // Website's Footer

/* ===== The Website "Pages" Components ===== */
import Placeholder from "./Placeholder"  // temporary main page placeholder
import About from "./components/Pages/About/About";
import Login from "./components/Login/Login";
import Signup from "./components/Signup/Signup";
import { ProductSearch } from "./components/ProductSearch/ProductSearch";

// import Landing from "./Pages/Landing";
// import Dashboard from "./Pages/Dashboard";
// import Login from "./Pages/Login";
// import LoadingPage from "./Pages/Loading";

const Layout: React.FC = () => {
  return (
    <BrowserRouter>
        {/* Navigation Bar is here to be persistent across website */}
        <NavigationBar /> 
        
        {/* This is where setting the webpages are located */}
        <Routes>
          {/* Individual route within this React App */}
          <Route path="/" element={<Placeholder />}/>
          <Route path="/about" element={<About />}/>
          <Route path="/login" element={<Login />}/>
          <Route path="/sign-up" element={<Signup />}/>
          <Route path="/product-search/search=:keyword" element={<ProductSearch />}/>
        </Routes>

        {/* The Footer is here to be persistent across website */}
        <Footer />
    </BrowserRouter>
  );
};

export default Layout;
