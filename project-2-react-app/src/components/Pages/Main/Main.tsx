// React
import React, { useRef, useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom'

// React-Bootstrap componenet
import Carousel from 'react-bootstrap/Carousel';
import Button from 'react-bootstrap/Button'
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Card from 'react-bootstrap/Card';
import Image from 'react-bootstrap/Image';


// Images for Carousel
import caro_img1 from "./carousel/choco-cake.jpg";
import caro_img2 from "./carousel/bread-and-wheat.jpg";

//Images for categories
import cate_bread from "./category/bread.jpg"
import cate_muffin from "./category/muffin.jpg"
import cate_cookie from "./category/cookie.jpg"
import cate_pie from "./category/pie.jpg"
import cate_cake from "./category/cake.jpg"

// Custom CSS
import "./custom.css";

//import
import MainProductBox from './product/MainProductBox';
import { productModel } from "../../models/productModel";


function Main(){
    
    // on page load
    useEffect(() => {
        //scroll to top on page load
        window.scrollTo({top: 0, left: 0, behavior: 'smooth'});
        // load products on load
        getBreadProducts();
        getCakeProducts();
        getCookieProducts();
        getMuffinProducts();
        getPieProducts();
    }, []);

    // the following code is to add ScrollToPageSection functionality 
    const breadCategory = useRef(null);
    const cakeCategory = useRef(null);
    const cookieCategory = useRef(null);
    const muffinCategory = useRef(null);
    const pieCategory = useRef(null);

    const scrollToSection = (elementRef:any) => {
        window.scrollTo({
            top: elementRef.current.offsetTop,
            behavior: 'smooth'
        })
    }

    //function to get products
    const { keyword } = useParams();
    const [productBreadList, setProductBreadList] = useState<productModel[]>([])
    const [productCakeList, setProductCakeList] = useState<productModel[]>([])
    const [productCookieList, setProductCookieList] = useState<productModel[]>([])
    const [productMuffinList, setProductMuffinList] = useState<productModel[]>([])
    const [productPieList, setProductPieList] = useState<productModel[]>([])

    async function getBreadProducts(){
        await fetch(`http://127.0.0.1:8080/products/category=bread`, {
            method: "GET",
            credentials: 'same-origin',
        })
        .then( response => response.json())
        .then( result => {
            setProductBreadList(result)
        } )
        .catch( (error) => {
            console.error(error)
        } )
    }

    async function getCakeProducts(){
        await fetch(`http://127.0.0.1:8080/products/category=cake`, {
            method: "GET",
            credentials: 'same-origin',
        })
        .then( response => response.json())
        .then( result => {
            setProductCakeList(result)
        } )
        .catch( (error) => {
            console.error(error)
        } )
    }
    
    async function getCookieProducts(){
        await fetch(`http://127.0.0.1:8080/products/category=cookie`, {
            method: "GET",
            credentials: 'same-origin',
        })
        .then( response => response.json())
        .then( result => {
            setProductCookieList(result)
        } )
        .catch( (error) => {
            console.error(error)
        } )
    }

    async function getMuffinProducts(){
        await fetch(`http://127.0.0.1:8080/products/category=muffin`, {
            method: "GET",
            credentials: 'same-origin',
        })
        .then( response => response.json())
        .then( result => {
            setProductMuffinList(result)
        } )
        .catch( (error) => {
            console.error(error)
        } )
    }

    async function getPieProducts(){
        await fetch(`http://127.0.0.1:8080/products/category=pie`, {
            method: "GET",
            credentials: 'same-origin',
        })
        .then( response => response.json())
        .then( result => {
            setProductPieList(result)
        } )
        .catch( (error) => {
            console.error(error)
        } )
    }

    return (
    <main className='min-vh-100 bg-main'>
        {/* Main Page Advertising Carousel */}
        <section id="carousel">
            <Carousel variant="dark" className='border-bottom'>
                {/* Slide 1 */}
                <Carousel.Item interval={7500}>
                    {/* Slide Image */}
                    <img className="d-block w-100 carousel-item-img" src={caro_img1} alt="Slide 1"/>
                    
                    {/* Slide Caption Text */}
                    <Carousel.Caption className="text-dark caro-text-left">
                        <h3>The Sweet, Savory, and Delicious </h3>
                        <p>Find your next dinner roll to desert from a selection of freshly-baked goods.</p>
                    </Carousel.Caption>
                </Carousel.Item>

                {/* Slide 2 */}
                <Carousel.Item interval={7500}>
                    {/* Slide Image */}
                    <img className="d-block w-100 carousel-item-img" src={caro_img2} alt="Slide 2"/>
                    
                    {/* Slide Caption Text */}
                    <Carousel.Caption className="text-dark caro-text-right">
                        <h3>Join Us</h3>
                        <p>Get fresh baked goods deliver to you from local bakery near you. Sign up today!</p>
                        <Link to={"/sign-up"}><Button>Sign Up</Button></Link>
                    </Carousel.Caption>
                </Carousel.Item>
            </Carousel>
        </section>

        {/* Categories */}
        <section id="categories" className='mt-5 pb-5'>
            <Container fluid>
                {/* Section Header */}
                <Row><h2 className="mb-5 display-4"><u>Product Categories</u></h2></Row>

                <Row className='justify-content-center'>
                    {/* Bread Card */}
                    <Col sm="6" md="4" lg="2" className='mb-3'>
                        <Link to={"#"} onClick={() => scrollToSection(breadCategory)} className="text-decoration-none text-dark text-center">
                            <Card className="p-4">
                                    {/* <!-- product img --> */}
                                    <Image src={cate_bread} roundedCircle fluid={true} id="card-image"/>
                                    {/* <!-- text --> */}
                                    <div className="text-truncate mt-4"><h5>Bread</h5></div>
                            </Card>
                        </Link>
                    </Col>

                    {/* Cake Card */}
                    <Col sm="6" md="4" lg="2" className='mb-3'>
                        <Link to={"#"} onClick={() => scrollToSection(cakeCategory)} className="text-decoration-none text-dark text-center">
                            <Card className="p-4">
                                    {/* <!-- product img --> */}
                                    <Image src={cate_cake} roundedCircle fluid={true} id="card-image"/>
                                    {/* <!-- text --> */}
                                    <div className="text-truncate mt-4"><h5>Cake</h5></div>
                            </Card>
                        </Link>
                    </Col>

                    {/* Cookie Card */}
                    <Col sm="6" md="4" lg="2" className='mb-3'>
                        <Link to={"#"}  onClick={() => scrollToSection(cookieCategory)} className="text-decoration-none text-dark text-center">
                            <Card className="p-4">
                                    {/* <!-- product img --> */}
                                    <Image src={cate_cookie} roundedCircle fluid={true} id="card-image"/>
                                    {/* <!-- text --> */}
                                    <div className="text-truncate mt-4"><h5>Cookie</h5></div>
                            </Card>
                        </Link>
                    </Col>

                    {/* Muffin Card */}
                    <Col sm="6" md="4" lg="2" className='mb-3'>
                        <Link to={"#"}  onClick={() => scrollToSection(muffinCategory)}  className="text-decoration-none text-dark text-center">
                            <Card className="p-4">
                                    {/* <!-- product img --> */}
                                    <Image src={cate_muffin} roundedCircle fluid={true} id="card-image"/>
                                    {/* <!-- text --> */}
                                    <div className="text-truncate mt-4"><h5>Muffin</h5></div>
                            </Card>
                        </Link>
                    </Col>

                    {/* Pie Card */}
                    <Col sm="6" md="4" lg="2" className='mb-3'>
                        <Link to={"#"}  onClick={() => scrollToSection(pieCategory)}  className="text-decoration-none text-dark text-center">
                            <Card className="p-4">
                                    {/* <!-- product img --> */}
                                    <Image src={cate_pie} roundedCircle fluid={true} id="card-image"/>
                                    {/* <!-- text --> */}
                                    <div className="text-truncate mt-4"><h5>Pie</h5></div>
                            </Card>
                        </Link>
                    </Col>
                </Row>
            </Container>
        </section>

        {/* Custom horizontal rule - Section Divider*/}
        <div className="divider div-transparent div-stopper"/>

        {/* Bread Category */}
        <section className='mt-5 pb-5' ref={breadCategory}>
            <Container fluid>
                {/* Section Header */}
                <Row><h2 className="mb-5"><u>Bread Products</u></h2></Row>

                <Row className='justify-content-center'>
                    
                    {productBreadList.map((product) => <MainProductBox key={product.id} {...product}></MainProductBox>)}
                </Row>
            </Container>
        </section>

        {/* Custom horizontal rule - Section Divider*/}
        <div className="divider div-transparent div-stopper"/>

        {/* Cake Category */}
        <section className='mt-5 pb-5' ref={cakeCategory}>
            <Container fluid>
                {/* Section Header */}
                <Row><h2 className="mb-5"><u>Cake Products</u></h2></Row>

                <Row className='justify-content-center'>
                    {/* use function to post products */}
                    {productCakeList.map((product) => <MainProductBox key={product.id} {...product}></MainProductBox>)}
                </Row>
            </Container>
        </section>

        {/* Custom horizontal rule - Section Divider*/}
        <div className="divider div-transparent div-stopper"/>

        {/* Cookie Category */}
        <section className='mt-5 pb-5' ref={cookieCategory}>
            <Container fluid>
                {/* Section Header */}
                <Row><h2 className="mb-5"><u>Cookie Products</u></h2></Row>

                <Row className='justify-content-center'>
                    {/* use function to post products */}
                    {productCookieList.map((product) => <MainProductBox key={product.id} {...product}></MainProductBox>)}
                </Row>
            </Container>
        </section>

        {/* Custom horizontal rule - Section Divider*/}
        <div className="divider div-transparent div-stopper"/>

        {/* Muffin Category */}
        <section className='mt-5 pb-5' ref={muffinCategory}>
            <Container fluid>
                {/* Section Header */}
                <Row><h2 className="mb-5"><u>Muffin Products</u></h2></Row>

                <Row className='justify-content-center'>
                    {/* use function to post products */}
                    {productMuffinList.map((product) => <MainProductBox key={product.id} {...product}></MainProductBox>)}
                </Row>
            </Container>
        </section>

        {/* Custom horizontal rule - Section Divider*/}
        <div className="divider div-transparent div-stopper"/>

        {/* Pie Category */}
        <section className='mt-5 pb-5' ref={pieCategory}>
            <Container fluid>
                {/* Section Header */}
                <Row><h2 className="mb-5"><u>Pie Products</u></h2></Row>

                <Row className='justify-content-center'>
                    {/* use function to post products */}
                    {productPieList.map((product) => <MainProductBox key={product.id} {...product}></MainProductBox>)}
                </Row>
            </Container>
        </section>

    </main>
      
    );
}

export default Main;