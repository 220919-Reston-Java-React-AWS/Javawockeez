// React-Bootstrap Components
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Image from 'react-bootstrap/Image'

//importing images
import freshbread from './Images/fresh-bread.jpg';
import sharecookie from './Images/sharing-cookie.jpg';
import foodbank from './Images/food-bank.jpg';

//to scroll to the top of page when loaded
import {useEffect} from 'react';

function About(){
    useEffect(() => {
        // scroll to top on page load
        window.scrollTo({top: 0, left: 0, behavior: 'smooth'});
      }, []);

    return (
        <main className="min-vh-100 p-3 mb-5 about-page-theme">
            {/* Our Story Section */}
            <Container fluid className="mt-5 pb-5 ps-5 pe-5">
                <Row className='align-items-center justify-content-between text-start'>
                    {/* Image */}
                    <Col md={6} lg={5} className="order-md-2">
                        <Image src={freshbread} rounded fluid={true}/>
                    </Col>

                    {/* Text */}
                    <Col md={6} lg={6}>
                        {/* <!-- Preheading --> */}
                        <h6 className="heading-xxs mb-3 text-gray-400">Who we are</h6>
                        
                        {/* <!-- Heading --> */}
                        <h2 className="mb-7">Our Story</h2>
                        
                        {/* <!-- Text --> */}
                        <p className="fs-lg text-muted">
                            Here at Javawockeez, we believe that the best kind of baked goods are freshly baked and then delivered 
                            right after for the highest quality. Although store-ready goods are a great convenience, we want to provide
                            freshly-baked products to the local areas without the use of preservatives and long distance shipping. 
                        </p>
                        <p className="mb-0 fs-lg text-muted">
                            By partnering with local bakers who also agree with our philosophy, Javawockeez will deliver 
                            freshly-baked goods soon after they're ready for delivery.
                        </p>
                    </Col>
                </Row>
            </Container>

            <hr/>

            {/* About Store Section */}
            <Container fluid className="mt-5 pb-5 ps-5 pe-5">
                <Row className='align-items-center justify-content-between text-start'>
                    {/* Image */}
                    <Col md={6} lg={5} >
                        <Image src={sharecookie} rounded fluid={true}/>
                    </Col>

                    {/* Text */}
                    <Col md={6} lg={6}>
                        {/* <!-- Preheading --> */}
                        <h6 className="heading-xxs mb-3 text-gray-400">How it works</h6>
                        
                        {/* <!-- Heading --> */}
                        <h2 className="mb-7">About Our Store</h2>
                        
                        {/* <!-- Text --> */}
                        <p className="fs-lg text-muted">
                            Our goal is to deliver freshly-baked goods. When you make an order after selecting your products, your 
                            order is sent to the corresponding bakers who then prepare, bake, and quality check to build each part
                            of your order. Once your order is ready, we deliver your package to you as soon as possible.
                        </p>
                        <p className="mb-0 fs-lg text-muted">
                            Due to the popularity of some products, certain might be baked in large batches. We strive to make sure
                            that all goods delivered are made the same day. This assures we are selling quality, fresh baked goods.
                        </p>
                    </Col>
                </Row>
            </Container>
            
            <hr/>
            
            {/* What we do Section */}
            <Container fluid className="mt-5 pb-5 ps-5 pe-5">
                <Row className='align-items-center justify-content-between text-start'>
                    {/* Image */}
                    <Col md={6} lg={5} className="order-md-2">
                        <Image src={foodbank} rounded fluid={true}/>
                    </Col>

                    {/* Text */}
                    <Col md={6} lg={6}>
                        {/* <!-- Preheading --> */}
                        <h6 className="heading-xxs mb-3 text-gray-400">Supporting the local area</h6>
                        
                        {/* <!-- Heading --> */}
                        <h2 className="mb-7">What We Do</h2>
                        
                        {/* <!-- Text --> */}
                        <p className="fs-lg text-muted">
                            We partner with local barkeries to increase their reach by delivering their products as well.
                            You are able to see the brand on the product's details page. We hope this increases transparancy 
                            for consumers who care what they eat and where it comes from.
                        </p>
                        <p className="mb-0 fs-lg text-muted">
                            For leftover quanities that are past the "fresh" quality for delivery, we donate the rest to local
                            food banks and soup kitchens. This reduces the amount of food waste we produce and provide to those
                            that can use it the most. 
                        </p>
                    </Col>
                </Row>
            </Container>
        </main>
    
    );
}

export default About;